package Parnapple.mistbornmod.command;

import Parnapple.mistbornmod.util.Metal;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

//Thanks to the Allomancy Mod by legobmw99 for an example on how to do this
public class FeruchemyArgumentType implements ArgumentType<String> {
    public static final FeruchemyArgumentType INSTANCE = new FeruchemyArgumentType();
    private static final Set<String> types = Arrays.stream(Metal.values()).map(Metal::getName).collect(Collectors.toSet());
    private static final DynamicCommandExceptionType unknown_power = new DynamicCommandExceptionType(o -> new TranslatableComponent("commands.mistbornmod.unrecognized", o));

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        String in = reader.readUnquotedString();
        if (types.contains(in)) {
            return in;
        }
        throw unknown_power.create(in);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(types, builder).toCompletableFuture();
    }

    @Override
    public Collection<String> getExamples() {
        return types;
    }
}
