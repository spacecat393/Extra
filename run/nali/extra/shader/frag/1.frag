#version 100
precision highp float;

uniform sampler2D Texture0;
uniform sampler2D Texture1;

varying vec2 fragment_texcoord;

void main()
{
	vec4 texture0_vec4 = texture2D(Texture0, fragment_texcoord);
	vec4 texture1_vec4 = texture2D(Texture1, fragment_texcoord);
//	if (texture0_vec4.r != texture1_vec4.r)
//	{
//		texture0_vec4.r = texture0_vec4.r / 2.0 + texture1_vec4.r / 2.0;
//	}
//	if (texture0_vec4.g != texture1_vec4.g)
//	{
//		texture0_vec4.g = texture0_vec4.g / 2.0 + texture1_vec4.g / 2.0;
//	}
//	if (texture0_vec4.b != texture1_vec4.b)
//	{
//		texture0_vec4.b = texture0_vec4.b / 2.0 + texture1_vec4.b / 2.0;
//	}
	gl_FragColor = mix(texture0_vec4, texture1_vec4, 0.5);//texture0_vec4;
//	gl_FragColor = vec4(1, 1, 1, 1);
}
