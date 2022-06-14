# restore the model of the spaceship to the landed version
scoreboard players add @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] spaceship_cmd 1
execute as @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] store result entity @s ArmorItems[3].tag.CustomModelData int 1 run scoreboard players get @s spaceship_cmd

# resore the equipment model in the offhand of the armor stand
execute as @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] run execute store result entity @s HandItems[1].tag.CustomModelData int 1 run scoreboard players get @s equipment_cmd

# put all the armor pieces to their desired places on the player
item replace entity @s armor.head from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] weapon.offhand
item replace entity @s armor.chest from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet
item replace entity @s armor.legs from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.chest
item replace entity @s armor.feet from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.legs

# remove the space equipment from the armor stand
item replace entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] weapon.offhand with minecraft:air
item replace entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.chest with minecraft:air
item replace entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.legs with minecraft:air
item replace entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] armor.feet with minecraft:air

