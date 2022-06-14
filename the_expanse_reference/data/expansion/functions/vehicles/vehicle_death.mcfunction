kill @e[type=item,limit=1,sort=nearest,nbt={Item:{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{ship_blaster:1b}}}]

scoreboard players add @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] spaceship_cmd 1
execute as @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] store result entity @s ArmorItems[3].tag.CustomModelData int 1 run scoreboard players get @s spaceship_cmd

execute rotated ~ 0 run summon item ^-2 ^ ^1 {NoGravity:1b,PickupDelay:1,Tags:["chest_item"],Item:{id:"minecraft:stick",Count:1b}}
execute rotated ~ 0 run summon item ^-2 ^ ^1 {NoGravity:1b,PickupDelay:1,Tags:["legs_item"],Item:{id:"minecraft:stick",Count:1b}}
execute rotated ~ 0 run summon item ^-2 ^ ^1 {NoGravity:1b,PickupDelay:1,Tags:["feet_item"],Item:{id:"minecraft:stick",Count:1b}}
execute rotated ~ 0 run summon item ^-2 ^ ^1 {NoGravity:1b,PickupDelay:1,Tags:["head_item"],Item:{id:"minecraft:stick",Count:1b}}

data modify entity @e[type=item,tag=chest_item,limit=1,sort=nearest] Item set from entity @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,limit=1,sort=nearest] ArmorItems[1]
data modify entity @e[type=item,tag=legs_item,limit=1,sort=nearest] Item set from entity @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,limit=1,sort=nearest] ArmorItems[0]
data modify entity @e[type=item,tag=feet_item,limit=1,sort=nearest] Item set from entity @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,limit=1,sort=nearest] ArmorItems[2]
data modify entity @e[type=item,tag=head_item,limit=1,sort=nearest] Item set from entity @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,limit=1,sort=nearest] HandItems[1]

execute as @e[type=item,tag=head_item,limit=1,sort=nearest] store result entity @s Item.tag.CustomModelData int 1 run scoreboard players get @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] equipment_cmd

