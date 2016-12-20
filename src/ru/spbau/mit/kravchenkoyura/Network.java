package ru.spbau.mit.kravchenkoyura;

import java.util.function.Function;

/**
 * Created by YuryKravchenko on 07/12/2016.
 */
public interface Network {
    public void Disconnect();
    public void Connect(String ip, String port);
    public void Wait (String port);
    public void setOnReceiveListener(Runnable f);
}
