# antidupe
execute unless entity @s[nbt={Inventory:[{Slot:103b,id:"minecraft:carrot_on_a_stick",tag:{return_capsule:1b,onhead:1b}}]}] run clear @s minecraft:carrot_on_a_stick{return_capsule:1b,onhead:1b}
execute unless entity @s[nbt={Inventory:[{Slot:103b,id:"minecraft:carrot_on_a_stick",tag:{return_capsule:1b,onhead:1b}}]}] run loot replace entity @s armor.head loot expansion:vehicles/return_capsule_head
kill @e[type=item,nbt={Item:{id:"minecraft:carrot_on_a_stick",tag:{return_capsule:1b,onhead:1b}}},limit=1,sort=nearest,distance=..5]
tp @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ~ ~ ~ 
execute anchored eyes run particle minecraft:campfire_signal_smoke ~ ~-2 ~ 1 1 1 0 10 force
execute anchored eyes run particle minecraft:flame ~ ~-2 ~ .7 .7 .7 0.1 50 force
execute anchored eyes run particle minecraft:large_smoke ~ ~-2 ~ .7 .7 .7 0.1 50 force
