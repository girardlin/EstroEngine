#version 400 core

in vec3 v_Position;

uniform mat4 u_ModelMatrix;
uniform float u_NumberOfRows;
uniform vec2 u_Offset;

out vec2 f_TexCoords;

void main(void)
{
    gl_Position = u_ModelMatrix * vec4(v_Position, 1.0f);

    f_TexCoords = vec2((v_Position.x + 1.0f) / 2.0f, 1.0f - (v_Position.y + 1.0f) / 2.0f);
    f_TexCoords = (f_TexCoords / u_NumberOfRows) + u_Offset;
}