package view;

import controller.InterpretorController;

import java.io.IOException;

public class RunExample extends Command {
    private InterpretorController ctrl;
    RunExample(String key, String desc, InterpretorController ctrl){
        super(key,desc);
        this.ctrl = ctrl;
    }
    @Override
    public void execute(){
        try{
            ctrl.executeAll();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
