# restore the custom model data of the spaceship
summon minecraft:armor_stand ~ ~ ~ {Marker:0b,Invisible:1b,Tags:["antidupe"], ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{exp_spaceship:1b,onhead:1b,CustomModelData:1012199,display:{Name:'{"text":"Spaceship","italic":false}'}}}]}
execute store result entity @e[type=armor_stand,tag=antidupe,limit=1,sort=nearest] ArmorItems[3].tag.CustomModelData int 1 run scoreboard players get @s spaceship_cmd
item replace entity @s armor.head from entity @e[type=armor_stand,tag=antidupe,limit=1,sort=nearest] armor.head expansion:spaceship/enter
kill @e[type=armor_stand, tag=antidupe, limit=1,sort=nearest]