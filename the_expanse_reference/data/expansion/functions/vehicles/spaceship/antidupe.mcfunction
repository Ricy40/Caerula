clear @s[predicate=!expansion:nbt_checks/armor/spaceship_no_cmd] minecraft:carrot_on_a_stick{exp_spaceship:1b,onhead:1b}
execute unless predicate expansion:nbt_checks/armor/spaceship_no_cmd unless score @s exp_death matches 1.. run function expansion:vehicles/spaceship/dupe_correction
kill @e[type=item,predicate=expansion:nbt_checks/items/spaceship,limit=1,sort=nearest,distance=..5]
clear @s[predicate=!expansion:nbt_checks/selected_item/items/blasters] minecraft:carrot_on_a_stick{ship_blaster:1b} 1
execute unless entity @s[nbt={Inventory:[{Slot:-106b}]}] run item replace entity @s[predicate=!expansion:nbt_checks/selected_item/items/blasters] weapon.offhand with minecraft:carrot_on_a_stick{display:{Name:'{"text":"Fire!","color":"white","bold":false,"italic":false,"underlined":false}'},Unbreakable:1b,CustomModelData:1012006,ship_blaster:1b} 1
kill @e[type=item,predicate=expansion:nbt_checks/items/blasters,limit=1,sort=nearest,distance=..5]
