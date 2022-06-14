execute unless entity @s[nbt={Inventory:[{Slot:103b,id:"minecraft:carrot_on_a_stick",tag:{lunar_module:1b,onhead:1b}}]}] run clear @s minecraft:carrot_on_a_stick{lunar_module:1b,onhead:1b}
execute unless entity @s[nbt={Inventory:[{Slot:103b,id:"minecraft:carrot_on_a_stick",tag:{lunar_module:1b,onhead:1b}}]}] run loot replace entity @s armor.head loot expansion:vehicles/lunar_module_falling
kill @e[type=item,nbt={Item:{id:"minecraft:carrot_on_a_stick",tag:{lunar_module:1b,onhead:1b}}},limit=1,sort=nearest,distance=..5]

execute unless block ~ ~-3 ~ #expansion:expansion_air run function expansion:vehicles/lunar_module/touchdown

tp @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ~ ~ ~