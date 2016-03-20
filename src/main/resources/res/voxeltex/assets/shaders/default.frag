#version 330 core

layout (location = 0) out vec4 color;

uniform vec4 colour;

in vec4 pos;

void main() {
	color = colour / pos;
}
