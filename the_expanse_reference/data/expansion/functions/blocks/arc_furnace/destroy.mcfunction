kill @e[type=item,nbt={Item:{id:"minecraft:dropper"}},limit=1,sort=nearest]
execute if score @s exp_steel_lvl matches 124431.. run function expansion:blocks/arc_furnace/melt_iron/retrieve_steel
execute as @p unless entity @s[gamemode=creative] run loot spawn ~ ~ ~ loot expansion:blocks/arc_furnace
kill @s