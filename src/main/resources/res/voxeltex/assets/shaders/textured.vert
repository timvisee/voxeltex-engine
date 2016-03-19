#version 120

uniform mat4 pr_matrix;
uniform mat4 vw_matrix = mat4(1.0);
uniform mat4 ml_matrix = mat4(1.0);

void main() {
	gl_Position = pr_matrix * vw_matrix * ml_matrix * gl_Vertex;
	gl_TexCoord[0] = gl_MultiTexCoord0;
}
