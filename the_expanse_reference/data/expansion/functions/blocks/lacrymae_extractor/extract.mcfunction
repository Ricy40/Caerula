scoreboard players add @s exp_timer_1 1

execute as @s[scores={exp_timer_1=1200..}] run loot insert ~ ~1 ~ loot expansion:fuel/lacrymae
execute as @s[scores={exp_timer_1=1200..}] at @s run playsound minecraft:block.brewing_stand.brew block @a ~ ~ ~
execute as @s[scores={exp_timer_1=1200..}] run particle minecraft:witch ~ ~2 ~ 0.3 0.3 0.3 0 50 force
execute as @s[scores={exp_timer_1=1200..}] run particle minecraft:cloud ~ ~0.5 ~ 0.3 0.3 0.3 0 50 force

execute as @s[scores={exp_timer_1=1200..}] run fill ~ ~2 ~ ~ ~2 ~ minecraft:obsidian replace minecraft:crying_obsidian

execute as @s[scores={exp_timer_1=1200..}] run scoreboard players set @s exp_timer_1 0