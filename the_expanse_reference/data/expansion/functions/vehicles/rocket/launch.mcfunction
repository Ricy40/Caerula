execute if block ~ ~-1 ~ minecraft:obsidian if block ~1 ~-1 ~ minecraft:obsidian if block ~-1 ~-1 ~ minecraft:obsidian if block ~ ~-1 ~1 minecraft:obsidian if block ~ ~-1 ~-1 minecraft:obsidian if block ~1 ~-1 ~1 minecraft:obsidian if block ~-1 ~-1 ~-1 minecraft:obsidian if block ~-1 ~-1 ~1 minecraft:obsidian if block ~1 ~-1 ~-1 minecraft:obsidian run tag @s add launchpad_good

execute unless score @s[tag=!launchpad_good] launch_timer matches 0 as @p run function expansion:handy_tools/error_messages/launchpad

execute if entity @s[tag=launchpad_good] as @p[predicate=!expansion:nbt_checks/armor/space_equipment/equipment] run function expansion:handy_tools/error_messages/equip_space_equipment

execute unless score @s[tag=launchpad_good,scores={rocket_fuel_prct=1..}] launch_timer matches 0.. if entity @p[predicate=expansion:nbt_checks/armor/space_equipment/equipment] run scoreboard players set @s launch_timer 230