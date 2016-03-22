#version 120

uniform sampler2D texture;

uniform sampler2D test1;

void main() {
//	gl_FragColor = texture2D(texture, gl_TexCoord[0].st);

	gl_FragColor = texture2D(test1, gl_TexCoord[0].st);
}
