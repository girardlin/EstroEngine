package gameEngine;

import models.Mesh;
import org.joml.Vector3f;
import sprites.Sprite;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import sprites.SpriteTexture;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;

public class Loader
{
    private List<Integer> vaoList = new ArrayList<Integer>();
    private List<Integer> vboList = new ArrayList<Integer>();
    private List<Integer> textureList = new ArrayList<Integer>();

    Loader(){}

    public Mesh loadToVAO(float[] positions, float[] texCoords, int[] indices)
    {
        int VAO = GL30.glGenVertexArrays();
        vaoList.add(VAO);
        GL30.glBindVertexArray(VAO);

        storeDataInIndexBuffer(createIntBuffer(indices));
        storeDataInAttributeList(0, 3, createFloatBuffer(positions));
        storeDataInAttributeList(1, 2, createFloatBuffer(texCoords));

        GL30.glBindVertexArray(0);

        return new Mesh(VAO, indices.length);
    }

    public Mesh loadToVAO(float[] positions)
    {
        int VAO = GL30.glGenVertexArrays();
        vaoList.add(VAO);
        GL30.glBindVertexArray(VAO);

        storeDataInAttributeList(0, 3, createFloatBuffer(positions));

        GL30.glBindVertexArray(0);

        return new Mesh(VAO, positions.length / 3);
    }

    public Sprite loadSprite(String fileName, Vector3f position, Vector2f scale)
    {
        return new Sprite(new SpriteTexture(loadTexture(fileName)), position, scale);
    }

    public Sprite loadSprite(String fileName, Vector3f position)
    {
        return new Sprite(new SpriteTexture(loadTexture(fileName)), position);
    }

    public Sprite loadSprite(String fileName, int index, int rows, Vector3f position, Vector2f scale)
    {
        return new Sprite(new SpriteTexture(loadTexture(fileName), rows), index, position, scale);
    }

    public Sprite loadSprite(String fileName, int index, int rows, Vector3f position)
    {
        return new Sprite(new SpriteTexture(loadTexture(fileName), rows), index, position);
    }

    private int loadTexture(String fileName)
    {
        String filePath = "res/" + fileName;
        int textureID;

        ByteBuffer image;
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        image = STBImage.stbi_load(filePath, width, height, comp, 4);

        if (image == null)
        {
            System.out.println("Failed to load texture file: " + filePath + " - " + stbi_failure_reason());
        }

        textureID = glGenTextures();
        textureList.add(textureID);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        glGenerateMipmap(GL_TEXTURE_2D);
        return textureID;
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, FloatBuffer data)
    {
        int VBO = GL15.glGenBuffers();
        vboList.add(VBO);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void storeDataInIndexBuffer(IntBuffer data)
    {
        int IBO = GL15.glGenBuffers();
        vboList.add(IBO);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, IBO);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
    }

    private FloatBuffer createFloatBuffer(float[] data)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private IntBuffer createIntBuffer(int[] data)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public void cleanUp()
    {
        for(int vao:vaoList) { GL30.glDeleteVertexArrays(vao); }
        for(int vbo:vboList) { GL30.glDeleteBuffers(vbo); }
    }
}
