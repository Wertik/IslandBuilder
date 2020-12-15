package space.devport.wertik.builder;

import space.devport.utils.DevportPlugin;
import space.devport.utils.text.language.LanguageDefaults;

public class IslandBuilderLanguage extends LanguageDefaults {

    public IslandBuilderLanguage(DevportPlugin plugin) {
        super(plugin);
    }

    @Override
    public void setDefaults() {
        addDefault("Commands.Invalid-Placement", "&cInvalid placement.");
        addDefault("Commands.Invalid-Player", "&cThat player does not exist.");

        addDefault("Commands.List.Header", "&7Loaded placements:");
        addDefault("Commands.List.Format", "&8 - &f%name% &7(triggers: &f%triggers%&7)");

        addDefault("Commands.Run-Here.Not-On-Island", "&cYou have to be on an island.");
        addDefault("Commands.Run-Here.Done", "&7Placement &f%placement% &7run on island &f%island%");

        addDefault("Commands.Run.No-Island", "&cPlayer does not have an island.");
        addDefault("Commands.Run.Done", "&7Placement &f%placement% &7ran for the island of &f%player%");
    }
}
