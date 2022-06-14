execute if entity @s[tag=!module_top] run loot spawn ~ ~ ~ loot expansion:vehicles/lunar_module
execute if entity @s[tag=module_top] run loot spawn ~ ~ ~ loot expansion:vehicles/module_top
playsound block.anvil.use block @a ~ ~ ~
scoreboard players enable @s module_optns
execute positioned ~ ~1 ~ as @p at @s run tp @s ~ ~0.001 ~
tp @s ~ -360 ~
tp @e[type=villager,tag=lunar_villager,limit=1,sort=nearest] ~ ~-360 ~
tp @e[type=pig,tag=module_pig,distance=..5,limit=1,sort=nearest] ~ -360 ~