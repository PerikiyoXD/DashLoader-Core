package dev.quantumfusion.dashloader.core.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class DashUtil {
	@Nullable
	public static <I> void nullableC(@Nullable I in, @NotNull Consumer<I> func) {
		if (in == null) return;
		func.accept(in);
	}

	@Nullable
	public static <I, O> O nullable(@Nullable I in, @NotNull Function<I, O> func) {
		if (in == null) return null;
		return func.apply(in);
	}

	@Nullable
	public static <I1, I2, O> O nullable(@Nullable I1 in1, @Nullable I2 in2, @NotNull BiFunction<I1, I2, O> func) {
		if (in1 == null || in2 == null) return null;
		return func.apply(in1, in2);
	}
}
