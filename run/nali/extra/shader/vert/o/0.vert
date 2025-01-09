#version 100
precision highp float;

attribute vec3 vertex;
attribute vec2 texcoord;

uniform mat4 Project;
uniform mat4 View;

varying vec2 fragment_texcoord;

void main()
{
	gl_Position = Project * View * vec4(vertex, 1.0);
	fragment_texcoord = texcoord;
}
