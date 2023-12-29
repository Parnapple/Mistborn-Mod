package Parnapple.mistbornmod.command;

import Parnapple.mistbornmod.capability.feruchemy.IFeruchemistData;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.util.Metal;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Predicate;

public class FeruchemyCommand {

    private static Predicate<CommandSourceStack> permissions(int level) {
        return (player) -> player.hasPermission(level);
    }

    public FeruchemyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal("feruchemy").requires(permissions(0));

        root.then(Commands.literal("clear").requires(permissions(2))
                .executes((command) -> {
                    return clearPowers(command.getSource());
                }));

        root.then(Commands.literal("remove").requires(permissions(2))
                .then(Commands.argument("type", MetalArgumentType.INSTANCE)
                        .executes((command) -> {
                            return removePower(command);
                        })));

        root.then(Commands.literal("giveall").requires(permissions(2))
                .executes((command) -> {
                    return givePowers(command.getSource());
                }));

        root.then(Commands.literal("add").requires(permissions(2))
                .then(Commands.argument("type", MetalArgumentType.INSTANCE)
                        .executes((command) -> {
                            return givePower(command);
                        })));

        root.then(Commands.literal("get").requires(permissions(0))
                .executes((command) -> {
            return getPowers(command.getSource());
        }));

        dispatcher.register(root);
    }

    private int clearPowers(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(IFeruchemistData::removeAllPowers);

        source.sendSuccess(new TranslatableComponent("commands.mistbornmod.clear_feruchemy"), true);

        return 1;
    }

    private int removePower(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        String type = context.getArgument("type", String.class);

        player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
            Metal metal = Metal.valueOf(type.toUpperCase());
            data.removePower(metal);
        });

        TranslatableComponent messageBase = new TranslatableComponent("commands.mistbornmod.cleared_feruchemy");
        TextComponent message = new TextComponent(messageBase.getString() + type);

        context.getSource().sendSuccess(message, true);

        return 1;
    }

    private int givePowers(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(IFeruchemistData::giveAllPowers);

        source.sendSuccess(new TranslatableComponent("commands.mistbornmod.add_all_feruchemy"), true);

        return 1;
    }

    private int givePower(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        String type = context.getArgument("type", String.class);

        player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
            Metal metal = Metal.valueOf(type.toUpperCase());
            data.givePower(metal);
        });

        TranslatableComponent messageBase = new TranslatableComponent("commands.mistbornmod.added_feruchemy");
        TextComponent message = new TextComponent(messageBase.getString() + type);

        context.getSource().sendSuccess(message, true);

        return 1;
    }

    private int getPowers(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();

        StringBuilder powers = new StringBuilder();

        player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
            for(Metal metal: data.getPowers()) {
               powers.append(metal.getName()).append(", ");
            }
        });

        TranslatableComponent messageBase = new TranslatableComponent("commands.mistbornmod.get_feruchemy_powers");
        TextComponent message = new TextComponent(messageBase.getString() + powers);

        source.sendSuccess(message, true);


        return 1;
    }


}
