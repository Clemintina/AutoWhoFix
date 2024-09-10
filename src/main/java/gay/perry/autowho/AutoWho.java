package gay.perry.autowho;

import gay.perry.autowho.command.Info;
import gay.perry.autowho.events.ChatReceived;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.EventBus;

public class AutoWho implements ModInitializer {

    @Override
    public void preInit() {
        System.out.println("AutoWho successfully loaded!");
        EventBus.subscribe(new ChatReceived());
        CommandBus.register(new Info());
    }
}