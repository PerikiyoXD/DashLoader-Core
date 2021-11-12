package dev.quantumfusion.dashloader.core;

import dev.quantumfusion.dashloader.core.config.ConfigHandler;
import dev.quantumfusion.dashloader.core.io.IOHandler;
import dev.quantumfusion.dashloader.core.progress.ProgressHandler;
import dev.quantumfusion.dashloader.core.registry.RegistryHandler;
import dev.quantumfusion.dashloader.core.thread.ThreadHandler;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * The heart of dashloader, Handles Config, IO and the serialization
 */
public final class DashLoaderCore {
	public static DashLoaderCore CORE;
	public static RegistryHandler REGISTRY;
	public static ThreadHandler THREAD;
	public static ConfigHandler CONFIG;
	public static ProgressHandler PROGRESS;
	public static IOHandler IO;

	private static boolean INITIALIZED = false;
	// Basic Information
	private final Consumer<String> print;

	private DashLoaderCore(List<DashObjectClass<?, ?>> dashObjects, Consumer<String> print, Path cacheDir, Path configPath) {
		this.print = print;

		// Handlers
		REGISTRY = new RegistryHandler(dashObjects);
		THREAD = new ThreadHandler("DashLoaderCore property. UwU");
		CONFIG = new ConfigHandler("DashLoaderCore property. OwO", configPath);
		PROGRESS = new ProgressHandler("DashLoaderCore property. ^w^");
		IO = new IOHandler(dashObjects, "DashLoaderCore property. >w<", cacheDir);
		INITIALIZED = true;
	}

	public static void initialize(Path cacheDir, Path configPath, Collection<Class<?>> dashClasses, Consumer<String> print) {
		if (INITIALIZED) throw new RuntimeException("Core is already initialized");
		CORE = new DashLoaderCore(parseDashObjects(dashClasses), print, cacheDir, configPath);
	}

	private static List<DashObjectClass<?, ?>> parseDashObjects(Collection<Class<?>> dashClasses) {
		var out = new ArrayList<DashObjectClass<?, ?>>();
		for (Class<?> dashClass : dashClasses) {
			out.add(new DashObjectClass(dashClass));
		}
		return Collections.unmodifiableList(out);
	}

	// Print things
	public void info(String info) {
		print.accept("/info/ " + info);
	}

	public void warn(String info) {
		print.accept("/warn/ " + info);
	}

	public void error(String info) {
		print.accept("/error/ " + info);
	}
}
