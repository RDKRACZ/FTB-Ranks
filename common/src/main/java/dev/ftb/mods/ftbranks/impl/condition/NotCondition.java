package dev.ftb.mods.ftbranks.impl.condition;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import dev.ftb.mods.ftbranks.api.Rank;
import dev.ftb.mods.ftbranks.api.RankCondition;
import net.minecraft.server.level.ServerPlayer;

/**
 * @author LatvianModder
 */
public class NotCondition implements RankCondition {
	public final RankCondition condition;

	public NotCondition(Rank rank, SNBTCompoundTag tag) throws Exception {
		condition = rank.getManager().createCondition(rank, tag.getCompound("condition"));
	}

	@Override
	public String getType() {
		return "not";
	}

	@Override
	public boolean isRankActive(ServerPlayer player) {
		return !condition.isRankActive(player);
	}

	@Override
	public void save(SNBTCompoundTag tag) {
		SNBTCompoundTag c = new SNBTCompoundTag();
		c.putString("type", condition.getType());
		condition.save(c);
		tag.put("condition", c);
	}
}