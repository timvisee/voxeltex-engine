#version 120

uniform mat4 prMatrix;
uniform mat4 vwMatrix = mat4(1.0);
uniform mat4 mlMatrix = mat4(1.0);

void main() {
	gl_Position = prMatrix * vwMatrix * mlMatrix * gl_Vertex;
	gl_TexCoord[0] = gl_MultiTexCoord0;
}
