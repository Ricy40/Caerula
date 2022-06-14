execute store result entity @s ArmorItems[3].tag.fuel_lvl int 1 run scoreboard players get @s fuel_level
data modify entity @s ArmorItems[3].tag.CustomModelData set value 1012008

loot spawn ~ ~ ~ loot expansion:vehicles/buggy

data modify entity @e[type=item,nbt={Item:{tag:{moon_buggy:1b}}},limit=1,sort=nearest] Item set from entity @s ArmorItems[3]

playsound block.anvil.use player @a ~ ~ ~
scoreboard players enable @s buggy_optns
execute positioned ~ ~1 ~ as @p at @s run tp @s ~ ~0.001 ~
tp @s ~ -360 ~
tp @e[type=pig,tag=buggy_pig,distance=..5,limit=1,sort=nearest] ~ -360 ~


