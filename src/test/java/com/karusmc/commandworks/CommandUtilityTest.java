/*
 * Copyright (C) 2016 PanteLegacy @ karusmc.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.karusmc.commandworks;

import com.karusmc.commandwork.CommandUtility;
import com.karusmc.commandworks.mock.MockSubcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import org.junit.Test;

import static com.karusmc.commandworks.mock.MockBukkitObjectFactory.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandUtilityTest {
    
    @Test
    public void checkSenderIsPlayer_returnsFalse() {
        CommandSender sender = mockSender(true);
        
        boolean returned = CommandUtility.checkSenderIsPlayer(sender);
        
        verify(sender, times(1)).sendMessage("This is a player only command.");
        
        assertFalse(returned);
    }
    
    
    @Test
    public void checkSenderIsPlayer_returnsTrue() {
        CommandSender sender = mockPlayer(true);
        
        boolean returned = CommandUtility.checkSenderIsPlayer(sender);
        
        verify(sender, times(0)).sendMessage("This is a player only command");
        
        assertTrue(returned);
    }
    
    
    
    @Test
    public void checkSenderHasPermission_returnsFalse() {
        CommandSender sender = mockSender(false);
        
        boolean returned = CommandUtility.checkSenderHasPermission(sender, MOCK_PERMISSION);
        
        verify(sender, times(1)).sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        
        assertFalse(returned);
    }
    
    
    @Test
    public void checkSenderHasPermission_returnsTrue() {
        CommandSender sender = mockSender(true);
        
        boolean returned = CommandUtility.checkSenderHasPermission(sender, MOCK_PERMISSION);
        
        verify(sender, times(0)).sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        
        assertTrue(returned);
    }
    
    
    
    @Test
    public void checkArgumentLength_returnTrue() {
        
        CommandSender sender = mockSender(true);
        MockSubcommand command = new MockSubcommand();
        
        boolean returnedLower = CommandUtility.checkArgumentLength(sender, command, 1, 1, 3);
        boolean returnedUpper = CommandUtility.checkArgumentLength(sender, command, 1, 3, 3);
        
        verify(sender, times(0)).sendMessage(ChatColor.RED + command.getUsage());
        
        assertTrue(returnedLower);
        assertTrue(returnedUpper);
        
    }
    
    
    @Test
    public void checkArgumentLength_returnsFalse() {
        
        CommandSender sender = mockSender(true);
        MockSubcommand command = new MockSubcommand();
        
        boolean returnedLower = CommandUtility.checkArgumentLength(sender, command, 1, 0, 3);
        boolean returnedUpper = CommandUtility.checkArgumentLength(sender, command, 1, 4, 3);
        
        verify(sender, times(2)).sendMessage(ChatColor.RED + command.getUsage());
        
        assertFalse(returnedLower);
        assertFalse(returnedUpper);
        
    }
    
}