package com.example.examplemod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GMLCommand extends CommandBase {

	@Override
	public String getName() {
		return "loadGML";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		System.out.println("gmlcommand : " + args[0] + " " + args[1]);

	}

	@SubscribeEvent
	public void ServerTick(TickEvent.ServerTickEvent event) {

	}

}
