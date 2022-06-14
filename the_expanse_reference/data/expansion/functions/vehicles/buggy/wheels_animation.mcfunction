scoreboard players set @s[scores={exp_timer_1=1012015..}] exp_timer_1 1012007
scoreboard players add @s exp_timer_1 1
execute store result entity @s ArmorItems[3].tag.CustomModelData double 1 run scoreboard players get @s exp_timer_1
