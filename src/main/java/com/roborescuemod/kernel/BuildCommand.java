package com.roborescuemod.kernel;

import java.io.File;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class BuildCommand extends CommandBase {

	private String Path = null;
	File file;

	public String getPath() {
		return this.Path;
	}

	public void resetPath() {
		this.Path = null;
	}

	public String popPath() {
		String temp = this.Path;
		this.Path = null;
		return temp;
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
		file = new File(this.Path + "map.gml");
		if (!file.exists()) {
			this.Path = null;
			System.out.println("Not Found File");
		}

	}

}
