#version 100
precision highp float;

uniform sampler2D texture_sampler;

varying vec2 fragment_texcoord;

void main()
{
	gl_FragColor = texture2D(texture_sampler, fragment_texcoord);
}
