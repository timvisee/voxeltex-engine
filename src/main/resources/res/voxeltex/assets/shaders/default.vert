#version 120

in vec4 position;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

out vec4 pos;

void main() {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * position;
	pos = modelMatrix * position;
}
