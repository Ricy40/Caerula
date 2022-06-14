execute store result entity @s ArmorItems[3].tag.fuel_lvl int 1 run scoreboard players get @s rocket_fuel_lvl

loot spawn ~ ~ ~ loot expansion:vehicles/rocket

data modify entity @e[type=item,nbt={Item:{tag:{exp_rocket:1b}}},limit=1,sort=nearest] Item set from entity @s ArmorItems[3]

playsound block.anvil.use block @a ~ ~ ~
scoreboard players enable @s rocket_optns
execute positioned ~ ~1 ~ as @p at @s run tp @s ~ ~0.001 ~
tp @s ~ -360 ~
tp @e[type=pig,tag=rocket_pig,distance=..5,limit=1,sort=nearest] ~ -360 ~
tp @e[type=villager,tag=rocket_villager,limit=2,sort=nearest] ~ ~-360 ~