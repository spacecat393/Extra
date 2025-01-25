#version 100
precision highp float;

uniform sampler2D Texture;

varying vec2 fragment_texcoord;

void main()
{
	gl_FragColor = texture2D(Texture, fragment_texcoord);
}
