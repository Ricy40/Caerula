scoreboard players remove @s[type=player,scores={fuel_level=1..}] fuel_level 30
execute if score @s fuel_level matches ..0 run scoreboard players set @s fuel_level 0

particle minecraft:firework ~ ~1 ~ 0 0 0 0.1 100 force