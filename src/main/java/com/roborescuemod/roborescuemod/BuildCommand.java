package com.roborescuemod.roborescuemod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class BuildCommand extends CommandBase {

	private String Path = null;

	public String getPath() {
		return this.Path;
	}

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
		this.Path = args[0];
	}

}
