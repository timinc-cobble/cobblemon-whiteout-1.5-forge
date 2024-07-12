package us.timinc.mc.cobblemon.whiteout

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.events.battles.BattleFaintedEvent
import net.minecraftforge.event.server.ServerStartedEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod(FixedStarterIvs.MOD_ID)
object FixedStarterIvs {
    const val MOD_ID = "whiteout"

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    object Registration {
        @SubscribeEvent
        fun onInit(e: ServerStartedEvent) {
            CobblemonEvents.BATTLE_FAINTED.subscribe { handleBattleFainted(it) }
        }
    }

    private fun handleBattleFainted(battleFaintedEvent: BattleFaintedEvent) {
        val killed = battleFaintedEvent.killed
        val entity = killed.entity ?: return
        val owner = entity.owner ?: return
        if (killed.actor.pokemonList.all { it.health == 0 }) {
            owner.kill()
        }
    }
}