## ----- Run from expansion:vehicles/spaceship/exit ----- ##

# kill the spaceship blaster item
kill @e[type=item,limit=1,sort=nearest,nbt={Item:{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{ship_blaster:1b}}}]

# copy the spaceship item data saved inside the storage to the spaceship armor stand
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[3] set from storage expansion:player_armor data.armor[0].spaceship

# summon 4 placeholder items. one for every piece of armor
summon item ~ ~ ~ {NoGravity:1b,PickupDelay:10,Tags:["head_item","armor_stick"],Item:{id:"minecraft:stick",Count:1b}}
summon item ~ ~ ~ {NoGravity:1b,PickupDelay:10,Tags:["chest_item","armor_stick"],Item:{id:"minecraft:stick",Count:1b}}
summon item ~ ~ ~ {NoGravity:1b,PickupDelay:10,Tags:["legs_item","armor_stick"],Item:{id:"minecraft:stick",Count:1b}}
summon item ~ ~ ~ {NoGravity:1b,PickupDelay:10,Tags:["feet_item","armor_stick"],Item:{id:"minecraft:stick",Count:1b}}

# copy the respective armor data from the armor storage to the placeholder items
data modify entity @e[type=item,tag=head_item,limit=1,sort=nearest] Item set from storage expansion:player_armor data.armor[0].helmet
data modify entity @e[type=item,tag=chest_item,limit=1,sort=nearest] Item set from storage expansion:player_armor data.armor[0].chestplate
data modify entity @e[type=item,tag=legs_item,limit=1,sort=nearest] Item set from storage expansion:player_armor data.armor[0].leggings
data modify entity @e[type=item,tag=feet_item,limit=1,sort=nearest] Item set from storage expansion:player_armor data.armor[0].boots

# kill the remaining placeholder items in case the player wasn't wearing a full set of armor
kill @e[type=item,tag=armor_stick,limit=3,sort=nearest,nbt={Item:{id:"minecraft:stick",Count:1b}}]

# clear this entry of the armor storage array
data remove storage expansion:player_armor data.armor[0]