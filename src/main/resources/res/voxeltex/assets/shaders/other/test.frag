/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

#ifdef GL_ES
precision mediump float;
#endif

//attributes from vertex shader
varying vec4 vColor;
varying vec2 vTexCoord;

//our texture samplers
uniform sampler2D u_texture;   //diffuse map
uniform sampler2D u_normals;   //normal map

//values used for shading algorithm...
uniform vec2 Resolution = vec2(1.0, 1.0);      //resolution of canvas
uniform vec4 AmbientColor;    //ambient RGBA -- alpha is intensity

uniform vec3 LightPos;        //light position, normalized
uniform vec4 LightColor;      //light RGBA -- alpha is intensity
uniform vec3 Falloff;         //attenuation coefficients

void main() {
//    gl_FragColor = texture2D(u_texture, gl_TexCoord[0].st);

	//RGBA of our diffuse color
	vec4 DiffuseColor = texture2D(u_texture, vTexCoord);

	//RGB of our normal map
	vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;

	//The delta position of light
	vec3 LightDir = vec3(LightPos.xy - (gl_FragCoord.xy / Resolution.xy), LightPos.z);

	//Correct for aspect ratio
//	LightDir.x *= Resolution.x / Resolution.y;

	//Determine distance (used for attenuation) BEFORE we normalize our LightDir
	float D = length(LightDir);

	//normalize our vectors
	vec3 N = normalize(NormalMap * 2.0 - 1.0);
	vec3 L = normalize(LightDir);

	//Pre-multiply light color with intensity
	//Then perform "N dot L" to determine our diffuse term
	vec3 Diffuse = (LightColor.rgb * LightColor.a) * max(dot(N, L), 0.0);

	//pre-multiply ambient color with intensity
	vec3 Ambient = AmbientColor.rgb * AmbientColor.a;

	//calculate attenuation
	float Attenuation = 1.0 / ( Falloff.x + (Falloff.y*D) + (Falloff.z*D*D) );

	//the calculation which brings it all together
	vec3 Intensity = Ambient + Diffuse * Attenuation;
	vec3 FinalColor = DiffuseColor.rgb * Intensity;

	gl_FragColor = vColor * vec4(FinalColor, DiffuseColor.a);
}





//varying vec4 v_color;
//varying vec2 v_texCoords;
//uniform sampler2D texture; // u_texture
//uniform sampler2D u_normals;
//uniform vec3 light;
//uniform vec3 ambientColor;
//uniform float ambientIntensity;
//uniform vec2 resolution;
//uniform vec3 lightColor;
//uniform bool useNormals;
//uniform bool useShadow;
//uniform vec3 attenuation;
//uniform float strength;
//uniform bool yInvert;
//
//void main() {
//        //sample color & normals from our textures
//        vec4 color = texture2D(texture, v_texCoords.st);
//        vec3 nColor = texture2D(u_normals, v_texCoords.st).rgb;
//
//        //some bump map programs will need the Y value flipped..
//        nColor.g = yInvert ? 1.0 - nColor.g : nColor.g;
//
//        //this is for debugging purposes, allowing us to lower the intensity of our bump map
//        vec3 nBase = vec3(0.5, 0.5, 1.0);
//        nColor = mix(nBase, nColor, strength);
//
//        //normals need to be converted to [-1.0, 1.0] range and normalized
//        vec3 normal = normalize(nColor * 2.0 - 1.0);
//
//        //here we do a simple distance calculation
//        vec3 deltaPos = vec3( (light.xy - gl_FragCoord.xy) / resolution.xy, light.z );
//
//        vec3 lightDir = normalize(deltaPos);
//        float lambert = useNormals ? clamp(dot(normal, lightDir), 0.0, 1.0) : 1.0;
//
//        //now let's get a nice little falloff
//        float d = sqrt(dot(deltaPos, deltaPos));
//        float att = useShadow ? 1.0 / ( attenuation.x + (attenuation.y*d) + (attenuation.z*d*d) ) : 1.0;
//
//        vec3 result = (ambientColor * ambientIntensity) + (lightColor.rgb * lambert) * att;
//        result *= color.rgb;
//
//        gl_FragColor = v_color * vec4(result, color.a);
//}