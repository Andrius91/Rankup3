package sh.okx.rankup.prestige;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import sh.okx.rankup.Rankup;
import sh.okx.rankup.ranks.Rank;
import sh.okx.rankup.requirements.Requirement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
public class Prestige extends Rank {
  @Getter
  private final String from;
  @Getter
  private final String to;

  private Prestige(Rankup plugin, String next, String rank, Set<Requirement> requirements, List<String> commands, String from, String to) {
    super(plugin, next, rank, requirements, commands);
    this.from = from;
    this.to = to;
  }

  public static Prestige deserialize(Rankup plugin, ConfigurationSection section) {
    ConfigurationSection requirementsSection = section.getConfigurationSection("requirements");
    Validate.notNull(requirementsSection, "No requirements defined for section " + section.getName());
    Set<Requirement> requirements = plugin.getRequirementRegistry().getRequirements(requirementsSection);

    return new Prestige(plugin,
        section.getString("next"),
        section.getString("rank"),
        requirements,
        section.getStringList("commands"),
        section.getString("from"),
        section.getString("to"));
  }
}
