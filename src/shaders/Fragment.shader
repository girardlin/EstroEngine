#version 400 core

in vec2 f_TexCoords;

uniform sampler2D sampler;

out vec4 out_Color;

void main(void)
{
    out_Color = texture(sampler, f_TexCoords);
}