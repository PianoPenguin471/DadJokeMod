package me.PianoPenguin471.dadjokemod;

import me.PianoPenguin471.dadjokemod.commands.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
@Mod(modid = DadJokeMod.MODID, version = DadJokeMod.VERSION, name = "DadJokeMod")
public class DadJokeMod
{
    public static ItemStack savedItem = null;
    public static Minecraft mc = Minecraft.getMinecraft();
    public static final String MODID = "dadjokemod";
    public static final String VERSION = "1.0.1";
    int textCushion = 5;
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        initCommands();
    }
    
    public void initCommands() {
        ClientCommandHandler commandHandler = ClientCommandHandler.instance;
        commandHandler.registerCommand(new DadJokeCommand());
        commandHandler.registerCommand(new GetSessionStatsCommand());
        commandHandler.registerCommand(new CurrentArgsCommand());
        commandHandler.registerCommand(new SaveItemCommand());
        commandHandler.registerCommand(new LoadItemCommand());
    }
    
    public static String getDadJoke() throws IOException {
        URL url = new URL("https://icanhazdadjoke.com/");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Accept", "text/plain");
        connection.addRequestProperty("User-Agent", "Java-DadJokeMod-BY-PianoPenguin471");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code : " + responseCode);
        if (responseCode == HttpsURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
            return response.toString();
        }
        throw new IOException("Hey, something went wrong");
    }
    
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // Autosprint
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
    }
    
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (mc == null || mc.thePlayer == null || mc.theWorld == null) return;
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            mc.fontRendererObj.drawString("[" + Minecraft.getDebugFPS() + " FPS]", textCushion, 0, 0x0000FF);
            int ping = mc.getCurrentServerData() == null ? -1 : (int) mc.getCurrentServerData().pingToServer;
            String pingMessage = "[" + ping + " ms]";
            mc.fontRendererObj.drawString(pingMessage, textCushion, 8, 0xFF0000);
            ItemStack playerItem = mc.thePlayer.getHeldItem();
            if (playerItem == null) return;
            mc.fontRendererObj.drawString(playerItem.getDisplayName(), textCushion, 16, 0x00FF00);
            if (playerItem.getTagCompound() == null || playerItem.getTagCompound().getCompoundTag("display") == null) return;
            NBTTagList lore = playerItem.getTagCompound().getCompoundTag("display").getTagList("Lore", 8);
            mc.fontRendererObj.drawString(playerItem.serializeNBT().toString(), textCushion, 24, 0xFFABCD);
            if (lore == null) {
                System.out.println("Null lore");
                return;
            }
            for (int i = 0; i < lore.tagCount(); i++) {
                mc.fontRendererObj.drawString(lore.get(i).toString(), textCushion, 8*i+32, 0xFFFFFF);
                if (i == lore.tagCount() -1) {
                    mc.fontRendererObj.drawString(playerItem.getItem().getRegistryName(), textCushion, 8*i+40, 0xFFF00E);
                }
            }
        }
    }
}