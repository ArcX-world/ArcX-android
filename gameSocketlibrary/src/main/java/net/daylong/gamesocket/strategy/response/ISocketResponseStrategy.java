package net.daylong.gamesocket.strategy.response;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public abstract class ISocketResponseStrategy {

    private Set<Integer> cmds = new HashSet<>();






    public ISocketResponseStrategy() {
        addCmd();
    }

    /**
     * 是否当前需要的
     *
     * @param cmd
     * @return
     */
    public boolean isCurrCmd(Integer cmd) {
        return cmds.contains(cmd);
    }

    /**
     * 添加需要的指令
     */
    public abstract void addCmd();


    public abstract void issue(Integer cmd, JSONObject msg);

    public  void addCmd(Integer cmd){
        cmds.add(cmd);
    }

}
