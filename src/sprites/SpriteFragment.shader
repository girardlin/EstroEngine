#version 400 core

in vec2 f_TexCoords;

uniform sampler2D u_Sampler2D;

out vec4 out_Color;

void main(void)
{
    out_Color = texture(u_Sampler2D, f_TexCoords);
}