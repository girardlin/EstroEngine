#version 400 core

in vec3 v_Position;

void main(void)
{
    gl_Position = vec4(v_Position, 1.0f);
}