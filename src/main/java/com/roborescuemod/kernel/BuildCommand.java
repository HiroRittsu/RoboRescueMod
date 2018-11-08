package com.roborescuemod.kernel;

import java.io.File;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class BuildCommand extends CommandBase {

	public static String Path = null;

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
		BuildCommand.Path = args[0];
		File file = new File(BuildCommand.Path + "map.gml");
		if (!file.exists()) {
			BuildCommand.Path = null;
			RoboRescueMod.mc.player.sendChatMessage("Not Found File");
		}

	}

}
