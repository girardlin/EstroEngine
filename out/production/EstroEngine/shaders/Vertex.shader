#version 400 core

in vec3 v_Position;
in vec2 v_TexCoords;

out vec2 f_TexCoords;

void main(void)
{
    gl_Position = vec4(v_Position, 1.0f);
    f_TexCoords = v_TexCoords;
}