summon item ~ ~ ~ {Item:{id:"minecraft:raw_iron",Count:1b}}

scoreboard players remove @s exp_steel_lvl 124430

execute store result entity @e[type=item,nbt={Item:{id:"minecraft:raw_iron",Count:1b}},limit=1,sort=nearest] Item.Count byte 4 run scoreboard players get @s exp_steel_lvl