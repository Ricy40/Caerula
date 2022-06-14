effect give @s minecraft:wither 1 1 true
execute store result score @s cw_hp_old run data get entity @s Health
scoreboard players operation @s cw_hp_old -= @s exp_damage
execute unless entity @s[nbt={Invulnerable:1b}] store result entity @s Health float 1 run scoreboard players get @s cw_hp_old
scoreboard players reset @s cw_hp_old
scoreboard players reset @s exp_damage