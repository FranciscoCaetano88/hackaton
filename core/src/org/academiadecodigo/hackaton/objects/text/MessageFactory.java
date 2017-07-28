package org.academiadecodigo.hackaton.objects.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;

public class MessageFactory {

    private static String[] phrases = null;

    public static Message generateMessage() {

        FileHandle fileHandle = Gdx.files.internal("phrases.txt");
        String[] phrases = fileHandle.readString().split("\n");

        return new Message(phrases[MathUtils.random(0, phrases.length-1)]);

    }

}
