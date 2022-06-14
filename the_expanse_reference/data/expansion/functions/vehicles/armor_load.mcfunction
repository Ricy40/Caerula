# copy the spaceship item data from the armor data storage to the spaceship armor stand
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[3] set from storage expansion:player_armor data.armor[0].spaceship

# consequetively copy the data of all the armor pieces to the feet slot of the spaceship armor stand and then to the respective slot of the player(can't directly modify player nbt oof)
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[0] set from storage expansion:player_armor data.armor[0].helmet
item replace entity @s armor.head from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[0] set from storage expansion:player_armor data.armor[0].chestplate
item replace entity @s armor.chest from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[0] set from storage expansion:player_armor data.armor[0].leggings
item replace entity @s armor.legs from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[0] set from storage expansion:player_armor data.armor[0].boots
item replace entity @s armor.feet from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet

# clear the feet slot of the spaceship armor stand
item replace entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet with minecraft:air

# clear this entry of the armor storage array
data remove storage expansion:player_armor data.armor[0]