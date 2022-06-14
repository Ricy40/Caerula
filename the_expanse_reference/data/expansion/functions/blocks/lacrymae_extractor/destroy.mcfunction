fill ~ ~1 ~ ~ ~1 ~ minecraft:air replace minecraft:hopper
fill ~ ~ ~ ~ ~ ~ minecraft:air replace minecraft:dropper
kill @e[type=item,nbt={Item:{id:"minecraft:hopper"}},distance=..5]
kill @e[type=item,nbt={Item:{id:"minecraft:dropper"}},distance=..5]
execute as @p unless entity @s[gamemode=creative] run loot spawn ~ ~ ~ loot expansion:blocks/lacrymae_extractor
kill @s