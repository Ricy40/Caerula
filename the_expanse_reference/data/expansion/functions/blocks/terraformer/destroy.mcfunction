kill @e[type=minecraft:experience_orb,distance=..5]
kill @e[type=armor_stand,tag=shield_generator,limit=1,sort=nearest,distance=..1]
execute as @p unless entity @s[gamemode=creative] run loot spawn ~ ~ ~ loot expansion:blocks/terraformer
kill @s